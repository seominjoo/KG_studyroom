caltext1 = document.getElementById('ctext1');
caltext2 = document.getElementById('ctext2');

check = 'true';
result = 0;

function AC() {
    caltext1.value = "";
    caltext2.value = '0';
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
}

function calculate(preview){

    splited = preview.split(/(?<=[*/+-])|(?=[*/+-])/); 
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
        } else if(splited2[i]==="+") {
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
        }else{
        }
    }
    return splited2[0];
}