
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>  
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ZAP INVENTARIOS</title>

        <!-- Incluye los archivos CSS de Bootstrap -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

        <link href="Vistas/EstilosFondo.css" rel="stylesheet" type="text/css"/>


    </head>
    <body> 

        <div class="row">
            <div class="col-12"> 
                <nav class="navbar  navbar-expand-md navbar-dark bg-dark border-3 fixed-top border-bottom" id="menu" >
                    <div class="container-fluid">

                        <a  >
                            <img src="./img/llogo.jpg" alt="" height="80" width="80" style="left: 30px">
                        </a>


                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                                <li class="nav-item">
                                    <a  style="margin-left: 10px; border:none" class="btn btn-secondary" 
                                        href="ControladorMovimientos?accion=listar">Movimientos</a>
                                </li>



                                <li class="nav-item">
                                    <a style="margin-left: 10px;border:none" class="btn btn-secondary"
                                       href="ControladorFacturas?accion=listar">Facturas</a>

                                </li>


                                <%--Sirve para abrir otra pagina target="_blank"--%>

                                <li class="nav-item">
                                    <a style="margin-left: 10px;border:none" class="btn btn-secondary"
                                       href="ControladorProductos?accion=listar" >Productos</a>

                                </li>

                                <li class="nav-item">
                                    <a style="margin-left: 10px;border:none" class="btn btn-secondary"
                                       href="ControladorProveedores?accion=listar" >Proveedores</a>

                                </li>

                                <!--icono para varias ventanas multiples //-->
                                <li class="nav-item dropdown">
                                    <a  style="margin-left: 10px;border:none"  class="btn btn-warning"
                                        href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                        Registros Varios    
                                    </a>
                                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                        <li><a class="dropdown-item" href="ControladorUsuarios?accion=listar" ><b style="color: #0d6efd;">Usuarios</b></a></li>
                                        <li><hr class="dropdown-divider"></li>
                                        <li><a class="dropdown-item"   href="ControladorRecibo?accion=listar" ><b style="color: #0d6efd;">Recibo</b></a></li> 
                                        <li><hr class="dropdown-divider"></li>
                                        <li><a class="dropdown-item"    href="ControladorCategorias?accion=listar" ><b style="color: #0d6efd;">Categorias</b></a></li> 
                                        <li><hr class="dropdown-divider"></li>
                                        <li><a class="dropdown-item"     href="ControladorBodega?accion=listar"><b style="color: #0d6efd;">Bodega</b></a></li> 
                                        <li><hr class="dropdown-divider"></li>
                                    </ul>
                                </li>
                            </ul>
                            <form action="ControladorValidar" method="POST">
                                <div class="ml-xl-2">
                                    <!-- Botón de cierre de sesión -->              
                                    <button class="btn btn-outline-light my-btn" name="accion" value="Salir">Cerrar Sesión</button>
                                </div>
                            </form>                       
  
                            </ul>
                        </div>
                    </div>
                </nav>

                <%--<body style="background-image: url('img/fac.jpg'); background-size: cover; background" >--%>
                <%--    <img src="img/fac.jpg" alt="alt" style="width: 100%; height: auto;">--%>
                <!-- Contenedor principal centrado -->

                <div class="row">
                    <div class="col-md-4">
                        <div id="cuadro">
                            <h1>CONTROL DE INVENTARIOS ZAP</h1>
                            <img src="Vistas/Imagenes/MI.jpg" alt=""/>
                        </div>
                    </div>
                </div>

                <h2 id="mensaje" ><b> ${mensaje}</b></h1>


                    <style>

                        body {
                            margin: 0;
                            padding: 120px;
                            background-image: url('img/prin.jpg');
                            background-size: cover;
                            background-repeat: no-repeat;
                            font-family: sans-serif;
                            background-position: center;
                            background-attachment: fixed;


                        }

                        .navbar {
                            background-color: black; /* Color de fondo del navbar */
                            color: white; /* Color de texto del navbar */
                            padding: 10px; /* Espaciado interior del navbar */
                        }


                        #cuadro {
                            position: relative; /* Asegura que el posicionamiento sea relativo para utilizar z-index */
                            z-index: 1; /* Ajusta el z-index para que esté por encima de la imagen de fondo */
                            max-width: 600px;
                            margin: 50px auto;
                            background-size: cover;
                            margin-top: 200px;
                            background-position: center;
                            padding: 20px;
                            border-radius: 10px;
                            box-shadow: 0 0 10px rgba(0, 0, 0, 0.7);
                            display: flex;
                            align-items: center;
                            justify-content: space-between;
                        }


                        #cuadro h1 {
                            text-align: center;
                            color: #34ce57;
                            margin-top: 0;
                            font-family: 'Lucida Console', sans-serif; /* Cambiado a Arial, puedes usar otras fuentes como 'Georgia', 'Times New Roman', etc. */
                            text-shadow: 2px 2px 4px rgba(0, 255, 255, 0.9);
                            margin: 2px;
                            font-weight: bold; /* Agregado negrita */
                            font-size: 50px; /* Aumentado el tamaño de letra a 28px, ajusta según tus necesidades */
                            background: none;
                            transition: background-color 0.3s ease; /* Agrega una transición suave al color de fondo */
                            text-transform: uppercase; /* Convierte el texto en mayúsculas */
                        }
                        #cuadro h1:hover{
                            color: #ff0;
                            background: rgba(0, 0, 0, 0.5);
                            box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.9);
                            border-radius: 25px;

                        }

                        #cuadro img {
                            max-width: 150px; /* Ajusta el ancho máximo del logo según sea necesario */
                            max-height: 100px;
                            height: auto;
                            border-radius: 5px;
                        }
                          .btn {
                                    border-radius: 20px; /* Redondear los botones */
                                }

                    </style>




                    <!-- Bootstrap CSS y JavaScript -->
                    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
                            crossorigin="anonymous"
                    ></script>
                    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js"
                            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
                            crossorigin="anonymous"
                    ></script>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"
                            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
                            crossorigin="anonymous"
                    ></script>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
                            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
                            crossorigin="anonymous"
                    ></script>
                    </body>


                    </html>
