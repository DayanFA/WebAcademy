describe('Manage appointments', () => {

    it('should be able to create a new appointment', () => {

        cy.intercept('GET', '/atendimento/', {
            statusCode: 200,
            fixture: 'atendimentos.json'
        });

        cy.intercept('GET', '/convenio/', {
            statusCode: 200,
            fixture: 'convenios.json'
        });

        cy.intercept('GET', '/profissional/', {
            statusCode: 200,
            fixture: 'profissionais.json'
        });

        cy.intercept('GET', '/paciente/', {
            statusCode: 200,
            fixture: 'pacientes.json'
        });

        cy.intercept('GET', '/atendimento/horarios/3/2023-12-02', {
            statusCode: 200,
            body: []
        });

        cy.intercept('POST', '/atendimento/', {
            statusCode: 201
        });

        cy.visit('/');
        cy.get('nav a').first().click();
        cy.get('a#add').click();

        cy.get('select#profissional').select('Davi Jesus Mendes');
        cy.get('input#data').type('2023-12-02');
        cy.get('select#hora').select('14:00:00');
        cy.get('select#convenio').select('Unimed');
        cy.get('select#paciente').select('Helen Dutra Vilar');

        cy.fixture('atendimentos.json').then((appointments) => {
            cy.fixture('atendimento-insert.json').then((newAppointment) => {
                appointments.push(newAppointment);
                cy.intercept('GET', '/atendimento/', {
                    statusCode: 200,
                    body: appointments
                });
            });
        });

        cy.get('input[type="submit"]').click();

        cy.contains('Operação realizada com sucesso.');
        cy.get('table tbody tr:last').within(() => {
            cy.get('td').eq(0).contains('02/12/2023');
            cy.get('td').eq(1).contains('14:00');
            cy.get('td').eq(2).contains('Helen Dutra Vilar');
            cy.get('td').eq(3).contains('Davi Jesus Mendes');
        });

    });

});

