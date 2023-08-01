import React, {useState} from 'react';
import {useNavigate} from "react-router";
import {getToken} from "../../utils/JwtToken";
import axios from "axios";
import GenreDropdown from "../../components/GenreDropdown/GenreDropdown";

const BookCreatePage = () => {
    const [title, setTitle] = useState('');
    const [isbn, setIsbn] = useState('');
    const [language, setLanguage] = useState('');
    const [author, setAuthor] = useState('');
    const [publisher, setPublisher] = useState('');
    const [description, setDescription] = useState('');
    const [publicationDate, setPublicationDate] = useState('');
    const [pages, setPages] = useState(0);
    const [price, setPrice] = useState(0);
    const [storageNum, setStorageNum] = useState(0);
    const [genreId, setGenreId] = useState(0);
    const [image, setImage] = useState('');
    const navigate = useNavigate();

    const handleGenreChange = (genreId) => {
        setGenreId(genreId);
    };

    const handleImageUpload = (e) => {
        setImage(e.target.files);
    }

    const sendRequestToCreateBook = () => {
        const dto = {
            title: title,
            description: description,
            publicationDate: publicationDate,
            genreId: genreId,
            pages: pages,
            isbn: isbn,
            language: language,
            storageNum: storageNum,
            author: author,
            publisher: publisher,
            price: price
        };
        axios.post(
            "http://localhost:8081/book",
            dto,
            {
                headers: {
                    'Authorization': 'Bearer ' + getToken()
                }
            })
            .then(response => {
                sendImage(response.data.id);
            })
    }

    const sendImage = (bookId) => {
        const formData = new FormData();
        formData.append('image', image[0]);

        axios.post("http://localhost:8081/img/" + bookId, formData, {
            headers: {
                'Authorization': 'Bearer ' + getToken(),
                'Content-Type': 'multipart/form-data'
            }
        }).then(response => {
            navigate("/books/" + bookId);
        })
    }

    return (
        <div>
            <section className="py-6">
                <div className="flex flex-col items-center justify-center px-6 py-8 mx-auto lg:py-0">
                    <div
                        className="w-full rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-0">
                        <div className="p-6 space-y-4 md:space-y-6 sm:p-8">
                            <h1 className="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
                                Добавить новую книгу
                            </h1>
                            <form className="space-y-4 md:space-y-6">
                                <div>
                                    <label htmlFor="name"
                                           className="block mb-2 text-sm font-medium">
                                        Название книги
                                    </label>
                                    <input value={title}
                                           onChange={e => setTitle(e.target.value)}
                                           type="text"
                                           name="title"
                                           id="title"
                                           className="bg-gray-50 border border-gray-300 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5  dark:border-gray-600 dark:placeholder-gray-400  dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                           required/>
                                </div>
                                <div>
                                    <label htmlFor="description"
                                           className="block mb-2 text-sm font-medium text-gray-900 ">
                                        Описание
                                    </label>
                                    <input value={description}
                                           onChange={e => setDescription(e.target.value)}
                                           type="text"
                                           name="description"
                                           id="description"
                                           className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5  dark:border-gray-600 dark:placeholder-gray-400  dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                           required/>
                                </div>
                                <GenreDropdown handleGenreChange={handleGenreChange}/>
                                <div>
                                    <label htmlFor="publication_date"
                                           className="block mb-2 text-sm font-medium text-gray-900 ">
                                        Дата публикации
                                    </label>
                                    <input value={publicationDate}
                                           onChange={e => setPublicationDate(e.target.value)}
                                           type="date"
                                           name="publication_date"
                                           id="publication_date"
                                           className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5  dark:border-gray-600 dark:placeholder-gray-400  dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                           required/>
                                </div>
                                <div>
                                    <label htmlFor="pages"
                                           className="block mb-2 text-sm font-medium text-gray-900 ">
                                        Количество страниц
                                    </label>
                                    <input value={pages}
                                           onChange={e => setPages(e.target.value)}
                                           type="number"
                                           name="pages"
                                           id="pages"
                                           className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5  dark:border-gray-600 dark:placeholder-gray-400  dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                           required/>
                                </div>
                                <div>
                                    <label htmlFor="isbn"
                                           className="block mb-2 text-sm font-medium text-gray-900 ">
                                        ISBN
                                    </label>
                                    <input value={isbn}
                                           onChange={e => setIsbn(e.target.value)}
                                           type="text"
                                           name="isbn"
                                           id="isbn"
                                           className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5  dark:border-gray-600 dark:placeholder-gray-400  dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                           required/>
                                </div>
                                <div>
                                    <label htmlFor="language"
                                           className="block mb-2 text-sm font-medium text-gray-900 ">
                                        Язык
                                    </label>
                                    <input value={language}
                                           onChange={e => setLanguage(e.target.value)}
                                           type="text"
                                           name="language"
                                           id="language"
                                           className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5  dark:border-gray-600 dark:placeholder-gray-400  dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                           required/>
                                </div>
                                <div>
                                    <label htmlFor="author"
                                           className="block mb-2 text-sm font-medium text-gray-900 ">
                                        Автор
                                    </label>
                                    <input value={author}
                                           onChange={e => setAuthor(e.target.value)}
                                           type="text"
                                           name="author"
                                           id="author"
                                           className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5  dark:border-gray-600 dark:placeholder-gray-400  dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                           required/>
                                </div>
                                <div>
                                    <label htmlFor="publisher"
                                           className="block mb-2 text-sm font-medium text-gray-900 ">
                                        Издатель
                                    </label>
                                    <input value={publisher}
                                           onChange={e => setPublisher(e.target.value)}
                                           type="text"
                                           name="publisher"
                                           id="publisher"
                                           className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5  dark:border-gray-600 dark:placeholder-gray-400  dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                           required/>
                                </div>
                                <div>
                                    <label htmlFor="price"
                                           className="block mb-2 text-sm font-medium text-gray-900 ">
                                        Цена
                                    </label>
                                    <input value={price}
                                           onChange={e => setPrice(e.target.value)} type="text"
                                           name="price"
                                           id="price"
                                           className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5  dark:border-gray-600 dark:placeholder-gray-400  dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                           required/>
                                </div>
                                <div>
                                    <label htmlFor="storageNum"
                                           className="block mb-2 text-sm font-medium text-gray-900 ">
                                        Начальное количество товара
                                    </label>
                                    <input value={storageNum}
                                           onChange={e => setStorageNum(e.target.value)} type="text"
                                           name="storageNum"
                                           id="storageNum"
                                           className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5  dark:border-gray-600 dark:placeholder-gray-400  dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                           required/>
                                </div>
                                <div>
                                    <label htmlFor="fileInput"
                                           className="block mb-2 text-sm font-medium text-gray-900 ">
                                        Фото книги
                                    </label>
                                    <input
                                        type="file"
                                        name="fileInput"
                                        id="fileInput"
                                        className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5  dark:border-gray-600 dark:placeholder-gray-400  dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                        onChange={handleImageUpload}
                                        required
                                    />
                                </div>
                                <input
                                    type="button"
                                    onClick={sendRequestToCreateBook}
                                    value="Создать"
                                    className="w-full bg-gray-800 text-white hover:bg-gray-900 hover:cursor-pointer focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800"/>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    );
};

export default BookCreatePage;
