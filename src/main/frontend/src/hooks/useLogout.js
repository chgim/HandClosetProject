// hooks/useLogout.js
import { useState } from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";

const useLogout = () => {
    const [logoutDialogOpen, setLogoutDialogOpen] = useState(false);
    const navigate = useNavigate();
    const handleLogoutDialogOpen = () => {
        setLogoutDialogOpen(true);
    };

    const handleLogoutDialogClose = () => {
        setLogoutDialogOpen(false);
    };

    const handleLogout = async () => {
        const loginInfo = JSON.parse(localStorage.getItem("loginInfo"));

        if (loginInfo) {
            try {
                const response = await axios.delete(
                    "http://localhost:8090/members/logout",
                    {
                        headers: {
                            Authorization: `Bearer ${loginInfo.accessToken}`,
                        },
                        data: { refreshToken: loginInfo.refreshToken },
                    }
                );

                if (response.status === 200) {
                    localStorage.removeItem("loginInfo");
                    navigate("/LoginForm"); // 로그인 페이지로 이동
                }
            } catch (error) {
                console.error(error);
            }
        }

        setLogoutDialogOpen(false);
    };

    return {
        logoutDialogOpen,
        handleLogoutDialogOpen,
        handleLogoutDialogClose,
        handleLogout,
    };
};

export default useLogout;




// const handleLogout = async () => {
//     const loginInfo = JSON.parse(localStorage.getItem("loginInfo"));
//     if (loginInfo) {
//         try {
//             const accessToken = JSON.parse(
//                 localStorage.getItem("loginInfo")
//             ).accessToken;
//             const response = await fetch("http://localhost:8090/members/logout", {
//                 method: "DELETE",
//                 headers: {
//                     "Content-Type": "application/json",
//                     Authorization: `Bearer ${accessToken}`,
//                 },
//                 body: JSON.stringify({
//                     refreshToken: localStorage.getItem("loginInfo").refreshToken,
//                 }),
//             });
//             if (response.ok) {
//                 localStorage.removeItem("loginInfo");
//                 // localStorage.removeItem("isLoggedIn"); // 로그아웃 상태로 변경
//                 navigate("/LoginForm"); // 로그인 페이지로 이동
//             } else {
//                 console.error(response.statusText);
//             }
//         } catch (error) {
//             console.error(error);
//         }
//     }
//
// };