// import React from "react";
// import CategoryMenu from "../components/CategoryMenu";
// import ClosetItem from "../components/ClosetItem";
// const Closet = () => {
//   return ( // ClothingForm로 가는버튼구현 해야댐
//     <div>
//       <h1>Closet</h1>
//       <CategoryMenu />
//       <ClosetItem />
//     </div>
//   );
// };
//
// export default Closet;
import React, { useState } from "react";
import CategoryMenu from "../components/CategoryMenu";
import CategoryItem from "../components/CategoryItem";
import AddClothes from "../components/AddClothes";

function Closet() {
    const [selectedCategory, setSelectedCategory] = useState(null);
    const [selectedSubcategory, setSelectedSubcategory] = useState(null);
    const [selectedItems, setSelectedItems] = useState([]);

    const handleClickCategory = (category, subcategory, items) => {
        setSelectedCategory(category);
        setSelectedSubcategory(subcategory);
        setSelectedItems(items);
    };

    return (
        <div>
            <h1>Closet</h1>
            <CategoryMenu onClickCategory={handleClickCategory} />
            <CategoryItem
                category={selectedCategory}
                subcategory={selectedSubcategory}
                items={selectedItems}
            />
            <AddClothes/>
        </div>
    );
}

export default Closet;