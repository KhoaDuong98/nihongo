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

    const { book, level, page } = useParams()
    const { cateVocabulary, params, onChangeTab, onChangePage, search } = props
    const [vocabularies, setVocabularies] = React.useState([])
    const newParams = {
        ...params,
        categoryVocabulary: book,
        level: level
    }

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

    React.useEffect(() => {
        setValue(checkBook(book))
    }, [book])

    const handleChange = (event, index) => {
        setValue(index);

    };
    const handleChangeTab = (name) => {

        onChangeTab(name)

        navigate(`/vocabularies/${level}/${name}`);


    };
    React.useEffect(() => {

        const fetchVocabularies = async () => {

            const data = await vocabularyApi.getAll(newParams);
            console.log(data.vocabularies)
            setCount(data.totalPage)
            setTotalElements(data.totalElements)
            setVocabularies(data)

        };
        fetchVocabularies();

    }, [params]);

    const data = vocabularies.vocabularies
    const handleChangePage = (page) => {
        onChangePage(page);
        navigate(`/vocabularies/${level}/${book}/page/${page}`);

    }
    const handleSearch = (word) => {
        search(word)
    }
    console.log('count' + count)
    console.log('page' + page)

    return (
        <>

            <Box sx={{ width: '100%', bgcolor: 'background.paper' }}>
                <Tabs value={value} onChange={handleChange} centered>
                    {cateVocabulary.map(item => (
                        <Tab key={item.id} label={item.name} onClick={() => handleChangeTab(item.name)} />
                    ))}
                </Tabs>
            </Box>
            <Vocabulary search={handleSearch} pageParam={page} onPageChange={handleChangePage} totalElements={totalElements} count={count} vocabularies={data} params={newParams} />
        </>

    );
}
