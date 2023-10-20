
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import back from "../images/back.png";



const Container = styled.div`
  display: flex;
  flex-direction: column;
  //align-items: center;
  //align-items: flex-start;
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
  margin-top: 25px;
  margin-left: 9%;
  
`;



const Form = styled.form`
  display: flex;
  flex-direction: column;
`;



const Input = styled.input`
  padding: 8px;
  margin-bottom: 16px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 16px;
  margin-left: 9%;
  margin-right: 9%;
`;

const Button = styled.button`
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  margin-bottom: 16px;
  font-size: 17px;
  font-weight: bold;
  cursor: pointer;
  background-color: #364054;
  color: #fff;
  margin-left: 9%;
  margin-right: 9%;
`;

function LoginForm() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await fetch("http://localhost:8090/members/login", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ email, password }),
            });
            const data = await response.json();

            if (response.ok) {
                console.log(data);
                localStorage.setItem("loginData", JSON.stringify(data));
                // localStorage.setItem("isLoggedIn", "true"); // 로그인 상태를 저장
                navigate("/"); // 메인 페이지로 이동
            }
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <Container>
            <Header>
                <BackButton onClick={() => navigate("/MyPage")}>
                    <img src={back} alt="back" style={{ width: "28px" }} />
                </BackButton>

                </Header>
        <Form onSubmit={handleSubmit}>
          <div style={{color:"#364054", marginBottom:"20px",fontWeight:"bold", fontSize:"43px"}}>로그인</div>

          <Input
            type="email"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            placeholder="Email"
          />

          <Input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Password"
          />
          <Button type="submit">로그인하기</Button>
          <div onClick={() => navigate("/JoinForm")} style={{textAlign:"right", marginLeft:"9%",marginRight:"9%",color:"gray"}}>회원가입 하러가기</div>
        </Form>
                </Container>
    );
}

export default LoginForm;