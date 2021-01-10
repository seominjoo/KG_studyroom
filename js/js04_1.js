const a = 10;
/* 주로 다른 파일에서 바뀌면 안 되는 수치일 때 */

const person = {
    name: '길동',
    age: 123,
    kor: 90,
    eng: 80,
    math: 70,
    total: function(){
        return this.kor + this.eng + this.math;
    },
    // object 내부에서 함수 선언하는 방법
    get_avg: function () {
        return this.total() / 3;
    },
};