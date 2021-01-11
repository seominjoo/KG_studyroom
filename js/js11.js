caltext1 = document.getElementById('ctext1');
caltext2 = document.getElementById('ctext2');
modal = document.getElementById("calhistory");

check = 'true';
result = 0;

function AC() {
    caltext1.value = "";
    caltext2.value = '0';
    modal.innerHTML = "";
}

function del() {
    caltext2.value = caltext2.value.substring(0, caltext2.value.length-1);
}

function Output(num) {
    if(result === 1){
        caltext1.value = "";
        caltext2.value = '0';
    }
        if(caltext2.value === '0' && !(caltext2.value === '0.')){
                caltext2.value = "";
        }
        caltext2.value += num;
    check = 'true';
    result = 0;
}

function Calculation(sign) {
    if(result === 1){
        caltext1.value = caltext2.value;
        caltext2.value = '';
    }
    if(check === 'true' || caltext2.value === '0'){
        if(sign === '.'){
            if(!caltext2.value.includes('.')){
                caltext2.value += sign;
            }
        }else{
            caltext1.value += caltext2.value + sign;
            caltext2.value = '0';
        }
        check = 'false';
    }
    result = 0;
}

function calresult() {
    caltext1.value += caltext2.value;
    caltext2.value = calculate(caltext1.value);
    result = 1;
    modal.innerHTML += `<p>${caltext1.value}<br>${caltext2.value}</p>`
}

function calculate(string){

    splited = string.split(/(?<=[*/%()+-])|(?=[*/%+()-])/); 
    splited2 = splited

    for ( i = 0; i < splited2.length; i++) {
            temp = "";
            temp_int = 0;

        if (splited2[i]==="*") {
            temp_int = Number.parseInt(splited2[i - 1]) * Number.parseInt(splited2[i + 1]);
            temp = temp_int + "";
            splited2[i - 1]= temp;
            splited2.splice(i+1, 1);
            splited2.splice(i, 1);
            i--;
        } else if (splited2[i]==="/") {
            temp_int = Number.parseInt(splited2[i - 1]) / Number.parseInt(splited2[i + 1]);
            temp = temp_int + "";
            splited2[i - 1]= temp;
            splited2.splice(i+1, 1);
            splited2.splice(i, 1);
            i--;
        }else if (splited2[i]==="%") {
            temp_int = Number.parseInt(splited2[i - 1]) % Number.parseInt(splited2[i + 1]);
            temp = temp_int + "";
            splited2[i - 1] = temp;
            splited2.splice(i+1, 1);
            splited2.splice(i, 1);
            i--;
        }
    }
    for ( i = 0; i < splited2.length; i++) {
            temp = "";
            temp_int = 0;
    if(splited2[i]==="+") {
            temp_int = Number.parseInt(splited2[i - 1]) + Number.parseInt(splited2[i + 1]);
            temp = temp_int + "";
            splited2[i - 1]= temp;
            splited2.splice(i+1, 1);
            splited2.splice(i, 1);
            i--;
        } else if (splited2[i]==="-") {
            temp_int = Number.parseInt(splited2[i - 1]) - Number.parseInt(splited2[i + 1]);
            temp = temp_int + "";
            splited2[i - 1] = temp;
            splited2.splice(i+1, 1);
            splited2.splice(i, 1);
            i--;
        }
    }
    return splited2[0];
}

function caldisplay() {
    if( modal.style.display ==='none'){
        modal.style.display = "block";
    }else{
        modal.style.display="none";
    }
}