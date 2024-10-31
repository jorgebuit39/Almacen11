<%@page import="Persistencia.DaoPerfil"%>
<%@page import="Modelo.Perfil"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Usuarios"%>
<!siuuuuuuuuuuuuuuuuuuuuuuuu>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Usuarios</title>      
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>    
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <!-- DataTable -->
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.1/css/dataTables.bootstrap5.min.css">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/2.3.3/css/buttons.bootstrap5.min.css">

        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">


    </head>

    <body>

        <!--Barra de Navegacion -->
        <nav class="navbar  navbar-expand-md navbar-dark bg-dark border-3 fixed-top border-bottom" id="menu" >
            <div class="container-fluid"> 

                <a class="navbar-brand" href="#"></a>
                <img src="./img/llogo.jpg" alt="llogo" style="float: left; width: 40px;" >

                <button 
                    class="navbar-toggler" type="button" data-bs-toggle="collapse" 
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" 
                    aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>


                <div class="collapse navbar-collapse  " id="navbarSupportedContent">
                    <ul class="navbar-nav ml-3 me-auto">  
                        <form class="navbar-nav ms-auto ml-auto" role="search">
                            <input class="form-control " type="search" name="txtbuscar" placeholder="Buscar Nombre, CC" aria-label="Buscar">
                            <button  type="submit" name="accion" value="buscar" class="btn btn-primary ml-1">Buscar</button>
                        </form>

                    </ul>

                    <ul class="navbar-nav mb-5 mb-lg-0 float-start ">   

                        <li class="nav-item ">
                            <a class="nav-link active my-menu-item" aria-current="page" href="./index.jsp"><b>
                                    <span  style="margin-left: 50px; border:none" class="btn btn-info">
                                        <i class="bi bi-arrow-left-square-fill text-dark"></i> <b> Inicio </b>
                                    </span>
                            </a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link active" aria-current="page" href="ControladorUsuarios?accion=listar">
                                <span  style="margin-left: 50px; border:none" class="btn btn-warning">
                                    <i class="bi bi-plus-circle"></i> <b> Listar </b> </span>
                            </a>
                        </li>

                    </ul>
                </div>
            </div>
            <form action="ControladorValidar" method="POST">
                <div class="ml-xl-2">
                    <!-- Botón de cierre de sesión -->              
                    <button class="btn btn-outline-light my-btn" name="accion" value="Salir">Cerrar Sesión</button>
                </div>
            </form>
            <style>
                .my-btn {
                    font-family: 'Roboto', sans-serif;
                    white-space: nowrap;
                    padding: 0.5rem 1rem;
                    font-size: 12px;
                    border-radius: 0.25rem;
                }

                .my-btn:hover {
                    background-color: #f0f0f0;
                    cursor: pointer;
                    font-size: 12px;
                    font-weight: 700;
                }
            </style>

        </nav>

        <!--Barra de Navegacion -->

        formulario de registro
        <hr>
        <hr>
        <!--formulario -->

        <div class="row ">   
            <div class="col-md-4 ">
                <%--Formulario para registrar y sirve para editar --%>
                <div class="col-sm- bg-darkt card-body">
                    <hr>
                    <hr>
                    <h5 class="text-primary elegant-font"><b>FORMULARIO DE REGISTRO</b></h5> 

                    <form action="ControladorUsuarios" method="POST" autocomplete="off" class="custom-form">

                        <div class="row">
                            <div class="col-md-6 ">
                                <div class="form-group text-left">
                                    <label for="nombres" class="text-left">Nombres</label>
                                    <input type="text" class="form-control" value="${User.getNombres()}"  id="nombres" 
                                           name="nombres" placeholder="Ingrese Nombres">
                                </div>                           



                                <div class="form-group text-left">
                                    <label for="correo">Correo</label>
                                    <input type="email" class="form-control" id="correo" value="${User.getCorreo()}"
                                           name="correo" placeholder="Ingrese Correo">
                                </div>

                            </div>
                            <div class="col-md-6">        

                                <div class="form-group text-left">
                                    <label for="usuario">Usuario</label>
                                    <input type="text" class="form-control" id="usuario" value="${User.getUsuario()}" 
                                           name="usuario" placeholder="Ingrese Usuario">
                                </div>

                                <div class="form-group text-left">
                                    <label for="clave">Clave</label>
                                    <input type="password" class="form-control" id="clave" value="${User.getClave()}" 
                                           name="clave" placeholder="Ingrese Clave">
                                </div>

                                <div class="form-group text-left">
                                    <label for="tipoPerfil">Perfil</label>
                                    <select class="form-control" id="tipoPerfil" value="${User.getPerfil_idPerfil()}" name="perfil_idperfil">
                                        <option value="0">Seleccione perfil</option>
                                        <% List<Perfil> perfiles = DaoPerfil.listar();
                                            if (perfiles != null) {
                                                for (Perfil perfil : perfiles) {
                                                    if (perfil != null) {%>
                                        <option value="<%=perfil.getIdPerfil()%>"><%=perfil.getTipoperfil()%></option>
                                        <% }
                                                }
                                            }%>
                                    </select>

                                </div>
                            </div>
                        </div>
                </div>

                <div class="form-group mt-3 text-center" style="border: none;">

                    <button type="submit" name="accion" value="registrar" class="btn btn-warning">
                        <i class="fas fa-save "></i> Grabar</button>
                    <button type="submit" name="accion" value="editarUsuarios" class="btn btn-success">
                        <i class="bi bi-arrow-repeat"></i> Actualizar</button>

                    <button type="submit" name="accion" value="listar" class="btn btn-secondary">
                        <i class="bi bi-x-lg"></i> Borrar</button>

                </div>    

            </div>



            <div class="col-sm-8 mb-4 mt-5 sticky-top">
                <br>
                <h5 class="text-primary elegant-font"><b>LISTA USUARIOS</b></h5> 
                <div class=" table-container ml-3 md-3 table-responsive" >

                    <div class="container mt-6 bg-dark btn-group" >
                        <!--<table id="miTabla" class="table table-striped table-bordered" style="width:100%">-->

                        <table id="myTable"  class="table table-dark table-striped">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nombres</th>                      
                                    <th>Usuario</th>
                                    <th>Clave</th>
                                    <th>Correo</th>
                                    <th>Perfil</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    List<Usuarios> Lista = (List<Usuarios>) request.getAttribute("listaUsuarios");
                                    for (Usuarios usuarios : Lista) {%>
                                <tr>
                                    <td><%= usuarios.getIdUsuarios()%></td>
                                    <td><%= usuarios.getNombres()%></td>                    
                                    <td><%= usuarios.getUsuario()%></td>
                                    <td><%= usuarios.getClave()%></td>
                                    <td><%= usuarios.getCorreo()%></td>
                                    <td><%=DaoPerfil.obtenerNombrePerfil(usuarios.getPerfil_idPerfil())%></td>


                                    <td>
                                        <div class="btn-group" role="group" aria-label="Acciones">

                                            <a href="ControladorUsuarios?accion=eliminar&id=<%= usuarios.getIdUsuarios()%>"
                                               class="btn btn-danger btn-sm" onclick="return confirm('¿Estás seguro de que deseas eliminar este usuario?')">
                                                <i class="fas fa-trash"></i> <!-- Ícono de papelera -->
                                            </a>

                                            <a href="ControladorUsuarios?accion=editar&id=<%= usuarios.getIdUsuarios()%>" class="btn btn-primary btn-sm">
                                                <i class="fas fa-pencil-alt"></i> <!-- Ícono de lápiz -->
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                                <% }%>
                            </tbody>
                        </table>
                        <div  class="dataTable_bottom">  
                            <div class="dataTables_info"></div>
                            <div class="dataTables_paginate"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>   



        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

        <script type="text/javascript" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.js"></script>

        <!-- jQuery -->
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <!-- Bootstrap JS -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <!-- DataTables JS -->
        <script src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.24/js/dataTables.bootstrap4.min.js"></script>
        <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.7.1/js/dataTables.buttons.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.7.1/js/buttons.html5.min.js"></script>




        <script>
                                                   $(document).ready(function () {
                                                       $('#myTable').DataTable({
                                                           "paging": true, // Habilita la paginación
                                                           "pageLength": 7, // Número de registros por página
                                                           "language": {
                                                               "processing": "Procesando...",
                                                               "lengthMenu": "Mostrar _MENU_ registros por página",
                                                               "zeroRecords": "No se encontraron resultados",
                                                               "emptyTable": "Ningún dato disponible en esta tabla",
                                                               "info": "Mostrando _START_ a _END_ de _TOTAL_ entradas",
                                                               "infoEmpty": "Mostrando 0 a 0 de 0 entradas",
                                                               "infoFiltered": "(filtrado de un total de _MAX_ entradas)",
                                                               "search": "Buscar:",
                                                               "paginate": {
                                                                   "first": "Primero",
                                                                   "last": "Último",
                                                                   "next": "Siguiente",
                                                                   "previous": "Anterior"
                                                               },
                                                               "aria": {
                                                                   "sortAscending": ": Activar para ordenar la columna ascendente",
                                                                   "sortDescending": ": Activar para ordenar la columna descendente"
                                                               }
                                                           },
                                                           "dom": 'Bfrtip',
                                                           "buttons": [
                                                               {
                                                                   extend: 'pdfHtml5',
                                                                   text: '<i class="fas fa-file-pdf"></i>',
                                                                   titleAttr: 'Exportar a PDF',
                                                                   className: 'btn btn-danger btn-sm' // Añadir clase "btn-sm" para hacer los botones más pequeños
                                                               },
                                                               {
                                                                   extend: 'excelHtml5',
                                                                   text: '<i class="fas fa-file-excel" ></i>', // Aplicar el radio de borde al ícono de Excel
                                                                   titleAttr: 'Exportar a Excel',
                                                                   className: 'btn btn-success btn-sm' // Añadir clase "btn-sm" para hacer los botones más pequeños
                                                               }
                                                           ]
                                                       });
                                                   });
        </script>


        <style>
            .dataTables_info {
                font-family: 'Roboto', sans-serif;
                font-weight: 700;
                position: fixed;

                right:  38%;
                transform: translateX(-50%);
                margin-bottom: 15px; /* Ajusta según sea necesario */
                color: #09f;
            }


            #myTable {
                table-layout: fixed;
                width: 100%;
            }
            .btn {
                border-radius: 20px; /* Redondear los botones */
            }



        </style>

        <style>

            body {

                background-image: url('img/tyo.jpg');
                background-size: cover;

            }


            label{

                color:#000
            }

            h5{

                color: #ffffff
            }


        </style>


    </body>
</html>