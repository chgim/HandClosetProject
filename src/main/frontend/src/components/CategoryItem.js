import React, { useEffect, useState } from "react";
import { getAllClothesImages } from "../utils/api";
import styled from "styled-components";

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
      <ImageGrid>
        {items.map((item, index) => (
          <ImageItem key={item.id}>
            {category === "전체" ? (
              <ItemImage
                src={`data:image/jpeg;base64,${images[index]}`}
                alt={item.name}
              />
            ) : (
              <ItemImage src={item.image} alt={item.name} />
            )}
            <p>{item.name}</p>
          </ImageItem>
        ))}
      </ImageGrid>
    </div>
  );
}
const ImageGrid = styled.div`
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-gap: 20px;
  margin-left: 20px;
  margin-right: 20px;
`;

const ImageItem = styled.div`
  position: relative;
  width: 100%;
  height: 0;
  padding-bottom: 100%; /* 정사각형 비율을 유지하기 위한 패딩 */
  overflow: hidden;
`;

const ItemImage = styled.img`
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
`;
export default CategoryItem;
