import React from "react";
import { useLocation } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import back from "../images/back.png";
import styled from "styled-components";
import update from "../images/update.png";
const DiaryAdd = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const searchParams = new URLSearchParams(location.search);
  // const selectedDate = searchParams.get("selectedDate");
  const selectedDateUTC = new Date(searchParams.get("selectedDate"));

  // 날짜 출력 시 시간대 변환
  const formattedDate = selectedDateUTC.toLocaleDateString("ko-KR", {
    timeZone: "UTC", // 선택한 시간대에 맞게 변경
  });
  const handleSubmit = () => {
    navigate(`/Diary`);
  };

  return (
    <Container>
      <Header>
        <BackButton onClick={() => navigate("/Closet")}>
          <img src={back} alt="back" style={{ width: "34px" }} />
        </BackButton>
        <SubmitButton onClick={handleSubmit}>제출</SubmitButton>
      </Header>
      {/*<h2>Add Diary Entry for: {selectedDate}</h2>*/}
      <h2>Add Diary Entry for: {formattedDate}</h2>
      {/* Diary 추가 폼 또는 컴포넌트를 표시할 수 있습니다. */}
    </Container>
  );
};
const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const Header = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  width: 100%;
  height: 40px;
  margin-bottom: 30px;
`;

const BackButton = styled.div`
  margin-top: 23px;
  margin-left: 9%;
`;
const SubmitButton = styled.div`
  margin-top: 23px;
  margin-right: 9%;
  font-size: 20px;
  font-weight: bold;
`;
export default DiaryAdd;
