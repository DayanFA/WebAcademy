    <%@ page pageEncoding="UTF-8" %>

    <%@ page import="java.util.List" %>
    <%@ page import="br.ufac.sgcm.model.Usuario" %>

    <jsp:useBean id="controller"
        class="br.ufac.sgcm.controller.UsuarioController"
        scope="page" />

    <%
        List<Usuario> registros = controller.processListRequest(request);
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
                <div id="comandos">
                    <a href="UsuariosForm.jsp"
                    class="botao"
                    id="add">
                        Adicionar
                    </a>
                    <div>
                        <label for="busca">Busca</label>
                        <input type="search" name="busca" id="busca"
                            placeholder="Digite para buscar">
                    </div>
                </div>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Ativo</th>
                            <th>Nome Completo</th>
                            <th>Nome usuario</th>
                            <th>Papel</th>
                            <th>Senha</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Usuario item : registros) { %>
                        <tr>
                            <td class="fit"><%=item.getId()%></td>
                            <td><%=item.isAtivo()%></td>
                            <td><%=item.getNome_completo()%></td>
                            <td><%=item.getNome_usuario()%></td>
                            <td><%=item.getPapel()%></td>
                            <td><%=item.getSenha()%></td>
                            <td>
                                <a href="UsuariosForm.jsp?id=<%=item.getId()%>"
                                class="botao">Editar</a>
                                <a href="Usuarios.jsp?excluir=<%=item.getId()%>"
                                class="botao excluir">Excluir</a>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="8">Total de registros: <%=registros.size()%></td>
                        </tr>
                    </tfoot>
                </table>
            </main>
            <footer>
                <span>SGCM - Sistema de Gerenciamento de Clínica Médica</span>
                <span>Suporte técnico: (68) 5555-5555 | <a href="mailto:suporte.sgcm@ufac.br">suporte.sgcm@ufac.br</a></span>
            </footer>
            <!--<script>
                const campoBusca = document.getElementById('busca');
                const tabelaResultados = document.querySelector('tbody');

                campoBusca.addEventListener('input', () => {
                    const termoBusca = campoBusca.value.trim();

                    if (termoBusca.length >= 3) {
                        fetch(`/sgcm/profissional?busca=${termoBusca}`)
                            .then(response => response.json())
                            .then(data => {
                                // Limpe a tabela antes de adicionar os novos resultados
                                tabelaResultados.innerHTML = '';

                                // Adicione os resultados à tabela
                                data.forEach(item => {
                                    const row = document.createElement('tr');
                                    row.innerHTML = `
                                        <td class="fit">${item.id}</td>
                                        <td>${item.nome}</td>
                                        <td>${item.registroConselho}</td>
                                        <td>${item.especialidade.nome}</td>
                                        <td>${item.unidade.nome}</td>
                                        <td>${item.telefone}</td>
                                        <td>${item.email}</td>
                                        <td>
                                            <a href="profissionaisForm.jsp?id=${item.id}" class="botao">Editar</a>
                                            <a href="profissionais.jsp?excluir=${item.id}" class="botao excluir">Excluir</a>
                                        </td>
                                    `;
                                    tabelaResultados.appendChild(row);
                                });
                            })
                            .catch(error => {
                                console.error('Erro na requisição:', error);
                            });
                    } else {
                        // Se o termo de busca for menor que 3 caracteres, limpe a tabela de resultados
                        tabelaResultados.innerHTML = '';
                    }
                });
            </script>-->

            
        </body>
    </html>
