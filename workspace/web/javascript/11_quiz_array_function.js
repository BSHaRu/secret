// 11_quiz_array_function.html
// alert('js 파일 적용 확인!!!'); -> 확인되는지 test 용도로 사용됨

// 점수를 등록할 배열에 점수를 입력받아 등록
function inputScore(arrays, num, subject){ 
    //여기서 num는 html파일의 상수(const)의 변수(STUDENT_NUM)을 의미함
    for(let i = 0; i < num; i++){
        let score = prompt(subject + "과목에 점수를 입력해주세요.");
        score = parseInt(score);
        arrays[i] = score; // arrays.push(score); 로 써도 됨
    }
    // 점수 등록 완료된 배열 반환
    return arrays;
}

// 최대값
function maxScore(arrays){
    let max = arrays[0];
    for(let score of arrays){ // for of : value 값을 알려줌
        if(score > max){
            max = score;
        }
    }
    return max;
}

// 최소값
function minScore(arrays){
    let min = arrays[0];
    for(let index in arrays){ // for in : index 번호 값을 알려줌
        if(min > arrays[index]){
            min = arrays[index];
        }
    }
    return min;
}

// 합계
function totalValue(arrays){
    let sum = 0;
    for(let i = 0; i < arrays.length; i++){
        sum = sum + arrays[i]; // sum += arrays[i]; 동일한 표현
    }
    return sum;
}

// 평균
function average(total, num){
    return total / num;
}