    <%@ page pageEncoding="UTF-8" %>

    <%@ page import="java.util.List" %>
    <%@ page import="java.util.Objects" %>
    <%@ page import="br.ufac.sgcm.model.TipoUsuario" %>

    <jsp:useBean id="controller"
        class="br.ufac.sgcm.controller.UsuarioController"
        scope="page" />

    <jsp:useBean id="item"
        class="br.ufac.sgcm.model.Usuario"
        scope="page" />

    <%
        item = controller.processFormRequest(request, response);
    %>

    <!DOCTYPE html>
    <html>
        <head>
            <meta name="viewport" content="width=device-width, user-scalable=no">
            <title>SGCM - Profissionais</title>
            <link rel="stylesheet" href="./css/estilo.css">
            <link rel="stylesheet" href="./css/estilo-tema-azul.css" id="link-tema">
            <script src="./js/script.js" defer></script>
        </head>
        <body>
            <header>
                <div id="logo">
                    <img src="imagens/logo_branco.png" alt="Logo SGCM">
                    <span id="titulo">SGCM</span>
                </div>
                <div id="usuarioInfo">
                    <span>Usuário: Administrador (admin)</span>
                    <span>Papel: ADMIN</span>
                    <select id="tema">
                        <option value="">Escolha um tema</option>
                        <option value="azul">Azul</option>
                        <option value="vermelho">Vermelho</option>
                        <option value="amarelo">Amarelo</option>
                    </select>
                    <a href="javascript:void(0)"
                    class="botao">Logout</a>
                </div>
            </header>
            <nav>
                <ul>
                    <li><a href="agenda.html">Agenda</a></li>
                    <li><a href="atendimento.html">Atendimento</a></li>
                    <li><a href="pacientes.html">Pacientes</a></li>
                    <li><a href="profissionais.html">Profissionais</a></li>
                    <li><a href="convenios.html">Convênios</a></li>
                    <li id="dropdown">
                        <a href="javascript:void(0)">
                            Configurações
                            <span>&#9660;</span>
                        </a>
                        <div id="dropdown_menu">
                            <a href="unidades.html">Unidades</a>
                            <a href="especialidades.html">Especialidades</a>
                            <a href="usuarios.html">Usuários</a>
                        </div>
                    </li>
                </ul>
            </nav>
            <main>
                <form method="post">

                    <input type="hidden" name="id"
                        value="<%=Objects.toString(item.getId(), "")%>">

                    <div class="grid">

                    <label for="ativo">Ativo</label>
                    <input type="text" name="ativo" id="ativo" required
                        value="<%=Objects.toString(item.isAtivo(), "")%>">

                    <label for="nome_completo">Nome Completo</label>
                    <input type="text" name="nome_completo" id="nome_completo" required
                        value="<%=Objects.toString(item.getNome_completo(), "")%>">

                    <label for="nome_usuario">Nome Usuario</label>
                    <input type="text" name="nome_usuario" id="nome_usuario" required
                        value="<%=Objects.toString(item.getNome_usuario(), "")%>">

                    <label for="papel">Papel</label>
                   <select name="papel" id="papel" required>
                    <option value=""></option>
                    <%
                        String selecionado = "";
                        List<String> tiposUsuarios = controller.getTiposUsuarios(); // Obtém a lista de tipos de usuário
                        for (String tipoUsuario : tiposUsuarios) {
                            selecionado = "";
                            if (item.getPapel() != null && item.getPapel().equals(tipoUsuario)) {
                                selecionado = "selected";
                            }
                    %>
                    <option value="<%=tipoUsuario%>" <%=selecionado%>><%=tipoUsuario%></option>
                    <% } %>
                </select>


                    <label for="senha">Senha</label>
                    <input type="senha" name="senha" id="senha" required
                        value="<%=Objects.toString(item.getSenha(), "")%>">

                    </div>

                    <input type="button" value="Cancelar" data-url="Usuarios.jsp">
                    <input type="submit" name="submit" value="Salvar">

                </form>
            </main>
            <footer>
                <span>SGCM - Sistema de Gerenciamento de Clínica Médica</span>
                <span>Suporte técnico: (68) 5555-5555 | <a href="mailto:suporte.sgcm@ufac.br">suporte.sgcm@ufac.br</a></span>
            </footer>
        </body>
        <script>
            console.log("Mensagem de depuração: Valor de item.getPapel() = <%= item.getPapel() %>");
        </script>
    </html>
