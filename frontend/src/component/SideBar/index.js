
import React, { useState } from 'react';
import styled from 'styled-components';
import { Link, Route, Routes, useMatch } from 'react-router-dom';
import * as FaIcons from 'react-icons/fa';
import * as AiIcons from 'react-icons/ai';
import { SidebarData } from './SidebarData';
import SubMenu from './SubMenu';
import { IconContext } from 'react-icons/lib';
import './index.css'
// import TabBar from '../../features/Vocabulary/page/Tab';
import NotFound from '../../features/Vocabulary/page/NotFound';
import VocabularyLoading from '../../features/Vocabulary/components/LoadingPage';
const LazyTabBar = React.lazy(() => import('../../features/Vocabulary/page/Tab'));

const Nav = styled.div`
  background: #661366;
  height: 80px;
  display: flex;
  justify-content: flex-start;
  align-items: center;
`;

const NavIcon = styled(Link)`
  margin-left: 2rem;
  font-size: 2rem;
  height: 80px;
  display: flex;
  justify-content: flex-start;
  align-items: center;
`;

const SidebarNav = styled.nav`
  background: #661366;
  width: 250px;
  height: 100vh;
  display: flex;
  justify-content: center;
  position: fixed;
  top: 0;
  left: ${({ sidebar }) => (sidebar ? '0' : '-100%')};
  transition: 350ms;
  z-index: 10;
`;


const SidebarWrap = styled.div`
  width: 100%;
`;
const Sidebar = (props) => {
    const [sidebar, setSidebar] = useState(false);
    const [params, setParams] = React.useState({
        categoryVocabulary: 'TANGO',
        level: '',
        word: '',
        page: 1
    }
    );
    const showSidebar = () => setSidebar(!sidebar);
    const handleClick = (level) => {
        setParams({
            ...params,
            level
        })
    }
    console.log(params)
    const cateVocabulary = [
        {
            id: 1,
            name: 'TANGO',
        },
        {
            id: 2,
            name: 'MIMIKARA',
        },
        {
            id: 3,
            name: 'SOUMATOME',
        }
    ]
    const handleOnChangeTab = (tabName) => {
        setParams({
            ...params,
            categoryVocabulary: tabName
        })
    }

    const handleOnChangePage = (page) => {
        console.log(page)
        setParams({
            ...params,
            page
        })
    }
    const handleSearch = (word) => {
        setParams({
            ...params,
            word,
            page: 1
        })
    }
    const handleGoToPage = (page) => {
        setParams({
            ...params,
            page
        })
    }
    const tabBarURL =
        <React.Suspense fallback={<VocabularyLoading />}>
            <LazyTabBar goToPage={handleGoToPage} search={handleSearch} params={params} onChangeTab={handleOnChangeTab} onChangePage={handleOnChangePage} cateVocabulary={cateVocabulary} />
        </React.Suspense>
    const baseURL = '/vocabularies/:level/:book';

    return (
        <>
            <IconContext.Provider value={{ color: '#fff' }}>

                <Nav>
                    <NavIcon to='#'>
                        <FaIcons.FaBars onClick={showSidebar} />
                    </NavIcon>
                    <h2 className='text'>Nihongo</h2>


                </Nav>

                <SidebarNav sidebar={sidebar}>

                    <SidebarWrap>
                        <NavIcon to='#'>
                            <AiIcons.AiOutlineClose onClick={showSidebar} />
                        </NavIcon>
                        {SidebarData.map((item, index) => {
                            return <SubMenu onLevelClick={handleClick} item={item} key={index} />;
                        })}
                    </SidebarWrap>
                </SidebarNav>
            </IconContext.Provider>
            <Routes>
                <Route path={baseURL} element={tabBarURL} />
                {/* <Route path={`${baseURL}/page/:page`} element={tabBarURL} /> */}
                {/* <Route path={`${baseURL}/search/:search`} element={tabBarURL} /> */}
                <Route path={`${baseURL}/*`} element={<NotFound />} />

            </Routes>

        </>
    );
};

export default Sidebar;

