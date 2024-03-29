import { Box, Pagination, Stack, TextField } from '@mui/material';
import React, { useRef, useState } from 'react';
import './index.css'
import usePagination from './Pagination';
Vocabulary.propTypes = {

};

function Vocabulary({ vocabularies, count, totalElements, onPageChange, search, pageParam, params, goToPage }) {
    let [page, setPage] = useState(Number(pageParam) || 1);
    const PER_PAGE = 10;
    const _DATA = usePagination(totalElements, PER_PAGE);
    const typingTimeOut = useRef(null)
    const typingTimeOut1 = useRef(null)
    const handleChange = (e, p) => {
        onPageChange(p)
        setPage(p);
        _DATA.jump(p);

    };

    let inputHandler = (e) => {
        const word = e.target.value.toLowerCase();
        console.log(word)
        if (!search) return
        if (typingTimeOut.current) {
            clearTimeout(typingTimeOut.current);
        }
        typingTimeOut.current = setTimeout(() => {
            search(word);
            setPage(1)
        }, 300);
    };
    const handleChangePage = (e) => {
        const page = e.target.value;
        if (typingTimeOut1.current) {
            clearTimeout(typingTimeOut1.current)
        }
        typingTimeOut1.current = setTimeout(() => {
            setPage(Number(page))
            goToPage(Number(page))
        }, 300);
        console.log(page)
    }

    return (
        <Box>
            <TextField
                id="outlined-basic"
                onChange={inputHandler}
                variant="outlined"
                label="Search"
            />

            <table className="table table-hover">
                {params.categoryVocabulary === 'TANGO' && (
                    <thead className='text-table'>
                        <tr>
                            <th scope="col">STT</th>
                            <th scope="col">Từ Vựng</th>
                            <th scope="col">Hiragana</th>
                            <th scope="col">Ý Nghĩa</th>
                            <th scope="col">Ví Dụ</th>

                        </tr>
                    </thead>
                )

                }
                {params.categoryVocabulary === 'MIMIKARA' && (
                    <thead className='text-table'>
                        <tr>
                            <th scope="col">STT</th>
                            <th scope="col">Từ Vựng</th>
                            <th scope="col">Hán Tự</th>
                            <th scope="col">Hiragana</th>
                            <th scope="col">Ý Nghĩa</th>
                        </tr>
                    </thead>
                )

                }
                {params.categoryVocabulary === 'SOUMATOME' && (
                    <thead className='text-table'>
                        <tr>
                            <th scope="col">STT</th>
                            <th scope="col">Từ Vựng</th>
                            <th scope="col">Hán Tự</th>
                            <th scope="col">Hiragana</th>
                            <th scope="col">Ý Nghĩa</th>
                        </tr>
                    </thead>
                )

                }
                <tbody>
                    {vocabularies && params.categoryVocabulary === 'TANGO' && vocabularies.map((vocabulary) => (
                        <tr className='text-table' key={vocabulary.id}>
                            <th scope="row">{vocabulary.vocabulary_id}</th>
                            <td>{vocabulary.word}</td>
                            <td>{vocabulary.read}</td>
                            <td>{vocabulary.mean}</td>
                            <td>{vocabulary.example}<br />{vocabulary.example1}</td>
                        </tr>
                    ))}
                    {vocabularies && params.categoryVocabulary === 'MIMIKARA' && vocabularies.map((vocabulary) => (
                        <tr className='text-table' key={vocabulary.id}>
                            <th scope="row">{vocabulary.vocabulary_id}</th>
                            <td>{vocabulary.word}</td>
                            <td>{vocabulary.kanJ}</td>
                            <td>{vocabulary.read}</td>
                            <td>{vocabulary.mean}</td>
                        </tr>
                    ))}
                    {vocabularies && params.categoryVocabulary === 'SOUMATOME' && vocabularies.map((vocabulary) => (
                        <tr className='text-table' key={vocabulary.id}>
                            <th scope="row">{vocabulary.vocabulary_id}</th>
                            <td>{vocabulary.word}</td>
                            <td>{vocabulary.kanJ}</td>
                            <td>{vocabulary.read}</td>
                            <td>{vocabulary.mean}</td>
                        </tr>
                    ))}


                </tbody>
            </table>
            <Stack spacing={2}>
                <TextField
                    value={page}
                    className='input_page'
                    id="outlined-basic"
                    onChange={handleChangePage}
                    variant="outlined"
                    label="Go to page"
                />
                <Pagination
                    size="large"
                    count={count}
                    page={page}
                    variant="outlined"
                    shape="rounded"
                    onChange={handleChange}
                />

            </Stack>


        </Box>

    );
}

export default Vocabulary;