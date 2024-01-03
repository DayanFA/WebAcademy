describe('Appointment status changes', () => {
    it('should show appointment after patient arrival', () => {

        cy.visit('/');
        cy.get('nav a').first().click();

        cy.get('table tbody tr:last').within(() => {
            cy.get('a.botao.confirmacao:not(.inativo)').click();
        });

        cy.get('table tbody tr:last').within(() => {
            cy.get('a.botao.chegada:not(.inativo)').click();
        });

        cy.get('nav a').eq(1).click();
        cy.get('table tbody tr:last').within(() => {
            cy.get('td').eq(0).contains('01/11/2022');
            cy.get('td').eq(1).contains('15:00');
            cy.get('td').eq(2).contains('Lucilene Santos Lucas');
            cy.get('td').eq(3).contains('Neuza Biango Nobrega');
        });
    });

    it('should remove appointment after completion', () => {
        
        cy.visit('/');
        cy.get('nav a').eq(1).click();

        cy.get('table tbody tr:last').within(() => {
            cy.get('a.botao.iniciar:not(.inativo)').click();
        });

        cy.get('table tbody tr:last').within(() => {
            cy.get('a.botao.excluir:not(.inativo)').click();
        });

        cy.get('table tbody tr:last').should('not.exist');
    });
});