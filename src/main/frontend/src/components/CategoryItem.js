// import React from "react";
//
// function CategoryItem({ category, subcategory, items }) {
//   if (!items || items.length === 0 || !category || !subcategory) {
//     return null;
//   }
//
//   return (
//     <div>
//       <h2>
//         {category} - {subcategory}
//       </h2>
//       <div>
//         {items.map((item) => (
//           <div key={item.id}>
//             {item.image ? (
//               <img src={item.image} alt={item.name} />
//             ) : (
//               <img src="../images/PlusIcon.png" alt="default" />
//             )}
//             <p>{item.name}</p>
//           </div>
//         ))}
//       </div>
//     </div>
//   );
// }
//
// export default CategoryItem;

//v2

// function CategoryItem({ category, subcategory, items }) {
//   return (
//     <div>
//       <h2>
//         {category} - {subcategory}
//       </h2>
//       <div>
//         {items.map((item) => (
//           <div key={item.id}>
//             {item.image ? (
//               <img
//                 src={
//                   typeof item.image === "string" || category === "전체"
//                     ? item.image
//                     : URL.createObjectURL(
//                         new Blob([item.image], { type: "image/jpeg" })
//                       )
//                 }
//                 alt={item.name}
//               />
//             ) : (
//               <img src="../images/PlusIcon.png" alt="default" />
//             )}
//             <p>{item.name}</p>
//           </div>
//         ))}
//         {items.length === 0 && (
//           <div>
//             <img src="../images/PlusIcon.png" alt="default" />
//             <p>No items available.</p>
//           </div>
//         )}
//       </div>
//     </div>
//   );
// }
//
// export default CategoryItem;

// useEffect(() => {
//   const fetchImageUrls = async () => {
//     const response = await fetch("/api/clothing/images/all");
//     const data = await response.json();
//     console.log(data);
//     const convertedImageUrls = data.map(
//       (imageBytes) => `data:image/jpeg;base64,${imageBytes}`
//     );
//     setImageUrls(convertedImageUrls);
//   };
//
//   fetchImageUrls();
// }, []);

// const getImageUrl = (item) => {
//   if (item.image) {
//     if (typeof item.image === "string") {
//       return item.image;
//     } else if (Array.isArray(item.image)) {
//       const imageUrl = imageUrls.find((url, index) => index === item.id);
//       if (imageUrl) {
//         return imageUrl;
//       }
//     }
//   }
//   return "../images/PlusIcon.png";
// };
// import React, { useEffect, useState } from "react";
//
// function CategoryItem({ category, subcategory, items }) {
//   const [imageUrls, setImageUrls] = useState([]);
//
//   useEffect(() => {
//     const fetchImageUrls = async () => {
//       try {
//         const response = await fetch("/api/clothing/images/all");
//         const data = await response.json();
//         console.log(data);
//         setImageUrls(data);
//       } catch (error) {
//         console.error("Failed to fetch image URLs:", error);
//       }
//     };
//
//     fetchImageUrls();
//   }, []);
//
//   const getImageUrl = (item) => {
//     if (item.imgPath) {
//       return `/api/clothing/images/${item.id}`;
//     }
//     return "../images/PlusIcon.png";
//   };
//
//   return (
//     <div>
//       <h2>
//         {category} - {subcategory}
//       </h2>
//       <div>
//         {items.map((item) => (
//           <div key={item.id}>
//             <img src={getImageUrl(item)} alt={item.name} />
//             <p>{item.name}</p>
//           </div>
//         ))}
//       </div>
//     </div>
//   );
// }
//
// export default CategoryItem;
// import React, { useEffect, useState } from "react";
// import { getAllClothesImages } from "../utils/api";
//
// function CategoryItem({ category, subcategory, items }) {
//   const [imageUrls, setImageUrls] = useState([]);
//
//   useEffect(() => {
//     const fetchImageUrls = async () => {
//       try {
//         const imageUrls = await getAllClothesImages();
//         setImageUrls(imageUrls);
//         console.log(imageUrls);
//       } catch (error) {
//         console.error("Failed to fetch image URLs:", error);
//       }
//     };
//
//     fetchImageUrls();
//   }, []);
//
//   const getImageUrl = (item) => {
//     if (item.imgPath) {
//       return `/api/clothing/images/${item.id}`;
//     }
//     return "../images/PlusIcon.png";
//   };
//
//   return (
//     <div>
//       <h2>
//         {category} - {subcategory}
//       </h2>
//       <div>
//         {items.map((item) => (
//           <div key={item.id}>
//             <img src={getImageUrl(item)} alt={item.name} />
//             <p>{item.name}</p>
//           </div>
//         ))}
//       </div>
//     </div>
//   );
// }
//
// export default CategoryItem;
import React, { useEffect, useState } from "react";
import { getAllClothesImages } from "../utils/api";

function CategoryItem({ category, subcategory, items }) {
  const [images, setImages] = useState([]);

  useEffect(() => {
    const fetchImages = async () => {
      try {
        const imageBytesList = await getAllClothesImages();
        setImages(imageBytesList);
      } catch (error) {
        console.error("Failed to fetch images:", error);
      }
    };

    fetchImages();
  }, []);

  return (
    <div>
      <h2>
        {category} - {subcategory}
      </h2>
      <div>
        {items.map((item, index) => (
          <div key={item.id}>
            {category === "전체" ? (
              <img
                src={`data:image/jpeg;base64,${images[index]}`}
                alt={item.name}
              />
            ) : (
              <img src={item.image} alt={item.name} />
            )}

            <p>{item.name}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default CategoryItem;
