describe('Manage appointments', () => {

    it('should be able to create a new appointment', () => {

        /*cy.intercept('GET', '/atendimento/', {
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
        });*/

        cy.visit('/');
        cy.get('nav a').contains('Convenios');
        cy.get('a#add').click();

        cy.get('input#nome').type('Unimed2.0');
        cy.get('input#razaoSocial').type('Unimed Rio Branco Cooperativa de Trabalho Médico Ltda.');
        cy.get('input#cnpj').type('32.900.030/0001-40');
        cy.get('input#representante').type('Jucilene Thais Medeiros');
        cy.get('input#email').type('Unimed2.0@gmail.com');
        cy.get('input#telefone').type('(68) 3223-6849');
        cy.get('input#ativo').type('True');

        /*cy.fixture('atendimentos.json').then((appointments) => {
            cy.fixture('atendimento-insert.json').then((newAppointment) => {
                appointments.push(newAppointment);
                cy.intercept('GET', '/convenio/', {
                    statusCode: 200,
                    body: appointments
                });
            });
        });*/

        cy.get('input[type="submit"]').click();

        cy.contains('Operação realizada com sucesso.');
        cy.get('table tbody tr:last').within(() => {
            cy.get('td').eq(0).contains('Unimed2.0');
            cy.get('td').eq(1).contains('Unimed Rio Branco Cooperativa de Trabalho Médico Ltda.');
            cy.get('td').eq(2).contains('32.900.030/0001-40');
            cy.get('td').eq(3).contains('Jucilene Thais Medeiros');
        });

    });

});

