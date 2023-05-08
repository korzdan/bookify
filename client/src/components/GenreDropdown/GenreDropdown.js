import React, {useEffect, useState} from 'react';
import axios from "axios";
import {getToken} from "../../utils/JwtToken";

const GenreDropdown = ({handleGenreChange}) => {
    const [genres, setGenres] = useState([]);
    const [selectedGenre, setSelectedGenre] = useState(1);

    useEffect(() => {
        async function fetchCategories() {
            try {
                const response = await axios.get("http://localhost:8081/genre", {
                    headers: {
                        'Authorization': 'Bearer ' + getToken()
                    }
                });
                setGenres(response.data);
            } catch (error) {
                console.error(error);
            }
        }

        fetchCategories();
    }, []);

    const handleSelectChange = (event) => {
        setSelectedGenre(event.target.value);
        handleGenreChange(event.target.value);
    };

    return (
        <div className="mb-4">
            <label
                htmlFor="category"
                className="block mb-2 text-sm font-medium text-gray-900"
            >
                Жанр
            </label>
            <select
                id="genre"
                name="genre"
                value={selectedGenre}
                onChange={handleSelectChange}
                className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:border-gray-600 dark:placeholder-gray-400 dark:focus:ring-blue-500 dark:focus:border-blue-500"
            >
                {genres.map((genre) => (
                    <option key={genre.id} value={genre.id}>
                        {genre.name}
                    </option>
                ))}
            </select>
        </div>
    );
};

export default GenreDropdown;
