import React from "react";
import PlusIcon from "../images/PlusIcon.png";
import { useNavigate } from "react-router-dom";
function AddClothes() {
    const navigate = useNavigate();

    return (
        <div>
            <div className="Plus">
                <img
                    src={PlusIcon}
                    alt="추가하기"
                    style={{ width: "15%" }}
                    onClick={() => {
                        navigate("/ClothingForm");
                    }}

                />

            </div>
        </div>
    );
}

export default AddClothes;