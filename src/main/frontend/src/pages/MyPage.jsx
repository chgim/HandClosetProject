import React from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";

const MyPage = () => {
    const navigate = useNavigate();

    const handleLogout = async () => {
        try {
            const accessToken = JSON.parse(
                localStorage.getItem("loginData")
            ).accessToken;
            const response = await fetch("http://localhost:8090/members/logout", {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${accessToken}`,
                },
                body: JSON.stringify({
                    refreshToken: localStorage.getItem("loginData").refreshToken,
                }),
            });
            if (response.ok) {
                localStorage.removeItem("loginData");
                // localStorage.removeItem("isLoggedIn"); // 로그아웃 상태로 변경
                navigate("/LoginForm"); // 로그인 페이지로 이동
            } else {
                console.error(response.statusText);
            }
        } catch (error) {
            console.error(error);
        }
    };
    // const isLoggedIn = localStorage.getItem("isLoggedIn") === "true";


    return (
        <div>
            <h1>MyPage</h1>
            {/*{isLoggedIn ? (*/}
                <Button onClick={handleLogout}>로그아웃</Button>
            {/*) : (*/}
                <Button onClick={() => navigate("/LoginForm")}>로그인 하러가기</Button>
            {/*)}*/}
        </div>
    );
};

const Button = styled.div`
  margin-top: 25px;
  margin-left: 9%;
`;

export default MyPage;