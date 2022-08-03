import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import Vocabulary from '../../features/Vocabulary/page/Vocabulary';


const SidebarLink = styled(Link)`
  display: flex;
  color: #e1e9fc;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
  list-style: none;
  height: 60px;
  text-decoration: none;
  font-size: 18px;
  &:hover {
    background: #480e48;
    border-left: 4px solid #632ce4;
    cursor: pointer;
    text-decoration: none;
    color:white;

  }
`;

const SidebarLabel = styled.span`
  margin-left: 16px;
`;

const DropdownLink = styled(Link)`
  background: #white;
  height: 60px;
  padding-left: 3rem;
  display: flex;
  align-items: center;
  text-decoration: none;
  color: #f5f5f5;
  font-size: 18px;
  &:hover {
    text-decoration: none;
    color:white;
    background: #480e48;
    cursor: pointer;
  }
`;

const SubMenu = (props) => {
  const [subnav, setSubnav] = useState(false);
  const { item, onLevelClick } = props
  const showSubnav = () => setSubnav(!subnav);
  const handleClick = (param) => {
    if (!onLevelClick) return;
    onLevelClick(param)


  }



  return (
    <>
      <SidebarLink to={item.url} onClick={item.subNav && showSubnav}>
        <div>
          {item.icon}
          <SidebarLabel>{item.title}</SidebarLabel>
        </div>
        <div>
          {item.subNav && subnav
            ? item.iconOpened
            : item.subNav
              ? item.iconClosed
              : null}
        </div>
      </SidebarLink>
      {subnav &&
        item.subNav.map((item, index) => {
          return (
            <DropdownLink onClick={() => handleClick(item.level)} to={`${item.url}${item.level}/TANGO`} key={index}>
              {item.icon}
              <SidebarLabel>{item.level}</SidebarLabel>

            </DropdownLink>

          );
        })}
    </>
  );
};

export default SubMenu;