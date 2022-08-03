import * as React from 'react';

import { Route, Routes, useParams } from 'react-router-dom';
import Sidebar from '../../../component/SideBar';
import TabBar from './Tab';


export default function Vocabularies(props) {

    const cateVocabulary = [
        {
            id: 0,
            name: 'TANGO',
        },
        {
            id: 1,
            name: 'MIMIKARA',
        },
        {
            id: 2,
            name: 'SOUMATOME',
        }
    ]

    return (
        <>
            <Sidebar />
            <Routes>
                <Route path='/vocabularies/:level/:book' element={<TabBar cateVocabulary={cateVocabulary} />} />
                <Route path='/vocabularies/:level/:book/page/:page' element={<TabBar cateVocabulary={cateVocabulary} />} />
                <Route path='/vocabularies/:level/:book/search/:search' element={<TabBar cateVocabulary={cateVocabulary} />} />

            </Routes>
        </>

    );
}
