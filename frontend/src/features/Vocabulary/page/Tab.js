import * as React from 'react';
import Box from '@mui/material/Box';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import vocabularyApi from '../../../api/VocabularyApi';
import { useParams, useNavigate, useMatch } from 'react-router-dom';
import VocabularyLoading from '../components/LoadingPage';
import { CircularProgress } from '@mui/material';
import LoadingPage from '../components/LoadingPage';
const LazyVocabulary = React.lazy(() => import('./Vocabulary'));

export default function TabBar(props) {
    let navigate = useNavigate();
    const [count, setCount] = React.useState()
    const [totalElements, setTotalElements] = React.useState()

    const { book, level, page } = useParams()
    const { cateVocabulary, params, onChangeTab, onChangePage, search, goToPage } = props
    const [vocabularies, setVocabularies] = React.useState([])
    const newParams = {
        ...params,
        categoryVocabulary: book,
        level,


    }
    const [loading, setLoading] = React.useState(true);

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

    // const sleep = (milliseconds) => {
    //     return new Promise(resolve => setTimeout(resolve, milliseconds))
    // }
    React.useEffect(() => {
        (async () => {
            try {
                window.scrollTo(0, 0)
                const data = await vocabularyApi.getAll(newParams);
                console.log(data.vocabularies)
                setCount(data.totalPage)
                setTotalElements(data.totalElements)
                setVocabularies(data)
            } catch (error) {
                console.log('Failed to fetch vocabularies list: ', error);
            }

            setLoading(false);
        })();
    }, [params]);

    const data = vocabularies.vocabularies
    const handleChangePage = (page) => {
        onChangePage(page);
        // navigate(`/vocabularies/${level}/${book}/page/${page}`);

    }
    const handleSearch = (word) => {
        search(word)
    }

    const handleGoToPage = (page) => {
        goToPage(page)
    }
    console.log(vocabularies)

    return (
        <>

            <Box sx={{ width: '100%', bgcolor: 'background.paper' }}>
                <Tabs value={value} onChange={handleChange} centered>
                    {cateVocabulary.map(item => (
                        <Tab key={item.id} label={item.name} onClick={() => handleChangeTab(item.name)} />
                    ))}
                </Tabs>
            </Box>
            {loading ? <CircularProgress color="secondary" />
                : data ?
                    <React.Suspense fallback={<LoadingPage />}>
                        <LazyVocabulary goToPage={handleGoToPage} search={handleSearch} pageParam={page} onPageChange={handleChangePage}
                            totalElements={totalElements} count={count}
                            vocabularies={data} params={newParams} />
                    </React.Suspense>
                    : <h2>Data Not Found</h2>
            }

        </>

    );
}
