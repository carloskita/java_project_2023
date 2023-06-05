// Função de validação do CPF
function validarCPF() {
  var cpf = document.getElementById("cpf").value;

  // Remove caracteres especiais do CPF
  cpf = cpf.replace(/[^\d]/g, "");

  // Verifica se o CPF tem 11 dígitos
  if (cpf.length !== 11) {
    alert("CPF inválido. O CPF deve conter 11 dígitos.");
    return false;
  }

  // Verifica se todos os dígitos são iguais (CPF inválido)
  if (/^(\d)\1+$/.test(cpf)) {
    alert("CPF inválido. Não são permitidos CPFs com todos os dígitos iguais.");
    return false;
  }

  // Validação dos dígitos verificadores
  var soma = 0;
  var resto;

  for (var i = 1; i <= 9; i++) {
    soma = soma + parseInt(cpf.substring(i - 1, i)) * (11 - i);
  }

  resto = (soma * 10) % 11;

  if (resto === 10 || resto === 11) {
    resto = 0;
  }

  if (resto !== parseInt(cpf.substring(9, 10))) {
    alert("CPF inválido. Por favor verifique seu CPF.");
    return false;
  }

  soma = 0;

  for (var i = 1; i <= 10; i++) {
    soma = soma + parseInt(cpf.substring(i - 1, i)) * (12 - i);
  }

  resto = (soma * 10) % 11;

  if (resto === 10 || resto === 11) {
    resto = 0;
  }

  if (resto !== parseInt(cpf.substring(10, 11))) {
    alert("CPF inválido. Verifique os dígitos verificadores.");
    return false;
  }

  // CPF válido
  alert("CPF válido!");
  return true;
}

// Adiciona o evento de validação ao formulário
var form = document.querySelector("form");
form.addEventListener("submit", function (event) {
  event.preventDefault(); // Impede o envio do formulário
  validarCPF(); // Chama a função de validação
});
// Função para formatar o CPF
function formatarCPF() {
  var cpfInput = document.getElementById("cpf");
  var cpf = cpfInput.value;

  // Remove caracteres especiais do CPF
  cpf = cpf.replace(/[^\d]/g, "");

  // Aplica a formatação do CPF (###.###.###-##)
  var formattedCPF = "";

  for (var i = 0; i < cpf.length; i++) {
    formattedCPF += cpf[i];

    if ((i + 1) % 3 === 0 && i < 8) {
      formattedCPF += ".";
    } else if (i === 8) {
      formattedCPF += "-";
    }
  }

  cpfInput.value = formattedCPF;
}

// Adiciona o evento de digitação ao campo de entrada do CPF
var cpfInput = document.getElementById("cpf");
cpfInput.addEventListener("keyup", formatarCPF);