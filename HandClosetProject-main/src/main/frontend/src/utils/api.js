import axios from "axios";

const getClothesByCategoryAndSubcategory = (category, subcategory) => {
    return axios.get(`/api/clothes?category=${category}&subcategory=${subcategory}`, {
        responseType: "arraybuffer" // 이 부분 추가
    }).then(response => {
        const base64 = btoa(
            new Uint8Array(response.data).reduce(
                (data, byte) => data + String.fromCharCode(byte),
                ""
            )
        );
        return `data:image/jpeg;base64,${base64}`; // base64 문자열을 이미지 URL로 반환
    });
};

export { getClothesByCategoryAndSubcategory };