describe('Manage unit', () => {

    it('should be able to create a new unit', () => {

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
        cy.get('nav a').eq(5).click();
        cy.get('#dropdown').click();
        cy.get('#dropdown_menu').invoke('css', 'display', 'block');
        cy.get('#dropdown_menu a').contains('Usuários').click();
        cy.get('#dropdown_menu').invoke('css', 'display', 'none');
        cy.get('a#add').click();

    
        cy.get('input[name="nomeCompleto"]').type('Dayan Freitas Alves');
        cy.get('input[name="nomeUsuario"]').type('DayanFa');
        cy.get('input[name="senha"]').type('1234');
        cy.get('input[type="checkbox"]').check();        
        cy.get('select[name="papel"]').select('ADMIN');
        cy.get('input[type="checkbox"]').check();
        
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
        cy.get('nav a').eq(5).click();
        cy.get('#dropdown').click();
        cy.get('#dropdown_menu').invoke('css', 'display', 'block');
        cy.get('#dropdown_menu a').contains('Usuários').click();
        cy.get('#dropdown_menu').invoke('css', 'display', 'none');

        cy.get('table tbody tr:last').within(() => {
            cy.get('td').eq(1).contains('Dayan Freitas Alves');
            cy.get('td').eq(2).contains('DayanFa');
            cy.get('td').eq(3).contains('Sim');
            cy.get('td').eq(4).contains('ADMIN');
        });

    });

});

