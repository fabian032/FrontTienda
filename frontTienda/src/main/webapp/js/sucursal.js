const mensaje=document.getElementById('mensaje');

const validarMenu =(m)=>{
    switch(m){
        case "usuarios":
        case "clientes":
            mensaje.innerHTML="Medellin";
            break;
        case "productos":
        case "proveedores":
            mensaje.innerHTML="Cali";
            break;
        case "ventas":
        case "reportes":
        case "consolidado":
            mensaje.innerHTML="Bogota";
            break;
        
    }
    return true
}