$('#loginForm').bootstrapValidator({

    message: 'Este valor no es valido',


    fields: {

        txtusuario: {

            validators: {

                notEmpty: {

                    message: 'El nombre de usuario es requerido'

                }

            }

        },

        txtpassword: {

            validators: {

                notEmpty: {

                    message: 'La contraseña es requerida'

                }

            }

        }

     }

});