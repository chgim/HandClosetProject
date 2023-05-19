
import React from "react";

function CategoryItem({ category, subcategory, items }) {
    if (!items || items.length === 0 || !category || !subcategory) {
        return null;
    }

    return (
        <div>
            <h2>
                {category} - {subcategory}
            </h2>
            <div>
                {items.map((item) => (
                    <div key={item.id}>
                        {item.image ? (
                            <img src={item.image} alt={item.name} />
                        ) : (
                            <img src="../images/PlusIcon.png" alt="default" />
                        )}
                        <p>{item.name}</p>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default CategoryItem;