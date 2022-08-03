
import React, { useState } from 'react';
import styled from 'styled-components';
import { Link, Route, Routes } from 'react-router-dom';
import * as FaIcons from 'react-icons/fa';
import * as AiIcons from 'react-icons/ai';
import { SidebarData } from './SidebarData';
import SubMenu from './SubMenu';
import { IconContext } from 'react-icons/lib';
import './index.css'

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
    const { onclickLevel } = props

    const showSidebar = () => setSidebar(!sidebar);
    const handleClick = (param) => {
        if (!onclickLevel) return;
        onclickLevel(param)
    }
    // const cateVocabulary = [
    //     {
    //         id: 1,
    //         name: 'TANGO',
    //     },
    //     {
    //         id: 2,
    //         name: 'MIMIKARA',
    //     },
    //     {
    //         id: 3,
    //         name: 'SATOME',
    //     }
    // ]

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


        </>
    );
};

export default Sidebar;

