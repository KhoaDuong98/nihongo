import * as React from 'react';
import Box from '@mui/material/Box';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Vocabulary from './Vocabulary';
import vocabularyApi from '../../../api/VocabularyApi';
import { useParams, useNavigate, useMatch } from 'react-router-dom';

export default function TabBar(props) {
    let navigate = useNavigate();
    const [count, setCount] = React.useState()
    const [totalElements, setTotalElements] = React.useState()

    const { book, level, page, search } = useParams()
    const { cateVocabulary } = props
    const [vocabularies, setVocabularies] = React.useState([])




    const checkBook = (book) => {
        if (book === 'TANGO') {
            return 0;
        } else if (book === 'MIMIKARA') {
            return 1;
        } else {
            return 2;
        }
    }
    const [value, setValue] = React.useState(checkBook(book));
    const [params, setParams] = React.useState({
        categoryVocabulary: book,
        level: level,
        word: search ?? '',
        page: page ?? 1
    });
    console.log(page)
    console.log(params)
    React.useEffect(() => {
        setParams({
            ...params,
            level: level

        })
    }, [level])


    React.useEffect(() => {
        setParams({
            ...params,
            categoryVocabulary: book

        })
    }, [book])

    React.useEffect(() => {
        setValue(checkBook(book))
        setParams({
            ...params,
            categoryVocabulary: book,

            level: level,

        })
    }, [book])

    const handleChange = (event, index) => {
        setParams({
            ...params,
            level: level,
            categoryVocabulary: book

        })
        setValue(index);

    };
    const handleChangeTab = (name) => {

        setParams({
            ...params,
            level: level,
            categoryVocabulary: name,
        })
        navigate(`/vocabularies/${level}/${name}`);


    };
    React.useEffect(() => {
        const fetchVocabularies = async () => {
            console.log(params)

            const data = await vocabularyApi.getAll(params);
            console.log(data.vocabularies)
            setCount(data.totalPage)
            setTotalElements(data.totalElements)
            setVocabularies(data)

        };
        fetchVocabularies();

    }, [params]);

    const data = vocabularies.vocabularies
    const handleChangePage = (page) => {
        setParams({
            ...params,
            page: page
        })
        navigate(`/vocabularies/${level}/${book}/page/${page}`);

    }
    const handleSearch = (word) => {

        setParams({
            ...params,
            word: word,
            page: 1

        })
        console.log(word)
    }
    return (
        <>

            <Box sx={{ width: '100%', bgcolor: 'background.paper' }}>
                <Tabs value={value} onChange={handleChange} centered>
                    {cateVocabulary.map(item => (
                        <Tab key={item.id} label={item.name} onClick={() => handleChangeTab(item.name)} />

                    ))}
                </Tabs>
            </Box>
            <Vocabulary search={handleSearch} pageParam={page} onChangePage={handleChangePage} totalElements={totalElements} count={count} vocabularies={data} params={params} />
        </>

    );
}
