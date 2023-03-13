function formateador() {
    var options2 = {style: "currency", currency: "USD"};
    return options2;
}

let correcto = 'success';
let error = 'danger';
let mensajeCorrecto = '\u00A1Transacci\u00F3n exitosa!';
let mensajeError = '\u00A1Error de transacci\u00F3n!'
let insertadoCorrecto = 'Se insert\u00F3 correctamente';
let modificadoCorrecto = 'Se modific\u00F3 correctamente';

$(':text,textarea').keyup('change', function () {
    $input = $(this);
    setTimeout(function () {
        var letra = $input.val().toUpperCase();
        letra = letra.normalize("NFD").replace(/[\u0300-\u036f]/g, "")
                .replace(/[^A-Z]0987654321+/g, ' ')
                .replace(/[*+?^${}|[\]\\]/g, '')
                .replace(/[!?¿#=|`{}¬~¨]/g, '');
        $input.val(letra);

    }, 10);
});

function alertaM(titulo, message, type, alerta, icono) {
    var wrapper = document.createElement('div');
    wrapper.innerHTML = '<div class="alert alert-' + type + ' alert-dismissible" role="alert"><i class="fas ' + icono + '"></i><strong>' + titulo + '</strong> ' + message + '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></div>';
    alerta.append(wrapper);
    setTimeout(() => {
        $('.alert.alert-' + type).alert('close');
    }, 5000);
}

function alertaModal(titulo, message, type, alerta) {
    var wrapper = document.createElement('div');
    wrapper.innerHTML = '<div class="alert alert-' + type + ' alert-dismissible" role="alert"><strong>' + titulo + '</strong> ' + message + '</div>';
    alerta.append(wrapper);
}