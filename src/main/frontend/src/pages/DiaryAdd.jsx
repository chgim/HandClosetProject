import React from "react";
import { useLocation } from "react-router-dom";

const DiaryAdd = () => {
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  // const selectedDate = searchParams.get("selectedDate");
  const selectedDateUTC = new Date(searchParams.get("selectedDate"));

  // 날짜 출력 시 시간대 변환
  const formattedDate = selectedDateUTC.toLocaleDateString("ko-KR", {
    timeZone: "UTC", // 선택한 시간대에 맞게 변경
  });

  return (
    <div>
      {/*<h2>Add Diary Entry for: {selectedDate}</h2>*/}
      <h2>Add Diary Entry for: {formattedDate}</h2>
      {/* Diary 추가 폼 또는 컴포넌트를 표시할 수 있습니다. */}
    </div>
  );
};

export default DiaryAdd;
