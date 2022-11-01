function formateador() {
    var options2 = {style: "currency", currency: "USD"};
    return options2;
}

$(':text,textarea').keyup('change', function () {
    $input=$(this);
    setTimeout(function () {
        var letra=$input.val().toUpperCase();
        letra = letra.normalize("NFD").replace(/[\u0300-\u036f]/g, "")
                .replace(/[^A-Z]0987654321+/g, ' ')
                .replace(/[*+?^${}|[\]\\]/g, '')
                .replace(/[!?¿#=|`{}¬~¨]/g, ''); 
        $input.val(letra);
        
    },10);
  });