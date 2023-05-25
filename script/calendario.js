document.addEventListener('DOMContentLoaded', function () {
    const monthsBR = ['janeiro', 'fevereiro', 'março', 'abril', 'maio', 'junho', 'julho', 'agosto', 'setembro', 'outubro', 'novembro', 'dezembro'];
    const tableDays = document.getElementById('dias');
    let ano = new Date().getFullYear(); // corrigido de "let year" para "let ano"
    let mes = new Date().getMonth();
  
    function GetdaysCalendar(mes, ano) {
      document.getElementById('mes').innerHTML = monthsBR[mes];
      document.getElementById('ano').innerHTML = ano;
  
      let firstDayOfWeek = new Date(ano, mes, 1).getDay();
      if (firstDayOfWeek === 0) { // corrigido para iniciar a semana no domingo
        firstDayOfWeek = 7;
      }
      let getLastDayThisMonth = new Date(ano, mes + 1, 0).getDate();
      
  
      for (let i = -firstDayOfWeek + 1, index = 0; i < (42 - firstDayOfWeek) + 1; i++, index++) {  //corrigido firstdayofweek +2 para +1
        let dt = new Date(ano, mes, i);
        let dayTable = tableDays.getElementsByTagName('td')[index];
        dayTable.classList.remove('mes-anterior');
        dayTable.classList.remove('proximo-mes');
        dayTable.innerHTML = dt.getDate();
  
        if (i < 1) {
          dayTable.classList.add('mes-anterior');
        }
        if (i > getLastDayThisMonth) {
          dayTable.classList.add('proximo-mes');
        }
  
      }
    }
  
    GetdaysCalendar(mes, ano);
  
    const botao_proximo = document.getElementById('btn_pro');
    const botao_anterior = document.getElementById('btn_ant');
  
    botao_proximo.onclick = function () {
      mes++;
      if (mes > 11) {
        mes = 0;
        ano++;
      }
      GetdaysCalendar(mes, ano);
  
    };
    botao_anterior.onclick = function () {
      mes--;
      if (mes < 0) {
        mes = 11;
        ano--;
      }
      GetdaysCalendar(mes, ano);
    };
  
  });
  