// for 기본문 
// document : 웹 페이지 객체. 웹페이지의 모든 요소들이 document내부에 들어있다.
// document.getElementById() : HTML요소를 ID속성으로 가져오는 자바스크립트 함수
const base_list = document.getElementById('base');
const array1=['마이쮸','새콤달콤','홀스','말랑카우'];

// for-in
/* javascript의 for...in은 object타입에 이용할 수 있다 */
const obj1={
    name:'프링글스',
    price:2500,
};

// 가져온 요소의 innerHTML에 값을 대입하면 웹페이지에 나온다. 
for(j=0;j<2;j++){
for(i=0;i<array1.length;i++){
    
    // base_list.innerHTML+='<li>'+array1[i]+'</li>';
    base_list.innerHTML+=`<li>${array1[i]}</li>`;
    // javascript string format
    }
}

//JAVA에서 Map을 KeySet()으로 꺼내 사용하는것과 유사함
const forin_list = document.getElementById('for-in');

for(property in obj1){
    console.log(`${property}:${obj1[property]}`)
    forin_list.innerHTML+=`<li><b>${property}</b> : ${obj1[property]}</li>`;
}

for(candy in array1){
    console.log(candy + ': ' + array1[candy]);
    forin_list.innerHTML+=`<li><b>[${candy}]</b> : ${array1[candy]}</li>`;
}

for(candy of array1){
    document.getElementById('for-of').innerHTML += `<li>${candy}</li>`
}

// iterable 객체만 for...of를 사용할 수 있다.
// for(value of obj1) {
//  console.log(value);
// }