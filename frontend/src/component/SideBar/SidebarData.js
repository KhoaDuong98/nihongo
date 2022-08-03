import React from 'react';
import * as AiIcons from 'react-icons/ai';
import * as IoIcons from 'react-icons/io';
import * as RiIcons from 'react-icons/ri';

export const SidebarData = [
    {
        title: 'Từ vựng',
        url: '/vocabularies',
        icon: <AiIcons.AiFillHome />,
        iconClosed: <RiIcons.RiArrowDownSFill />,
        iconOpened: <RiIcons.RiArrowUpSFill />,

        subNav: [
            {
                level: 'N1',
                url: 'vocabularies/',
                icon: <IoIcons.IoIosPaper />
            },
            {
                level: 'N2',
                url: 'vocabularies/',
                icon: <IoIcons.IoIosPaper />
            },
            {
                level: 'N3',
                url: 'vocabularies/',
                icon: <IoIcons.IoIosPaper />
            },
            {
                level: 'N4',
                url: 'vocabularies/',
                icon: <IoIcons.IoIosPaper />
            },
            {
                level: 'N5',
                url: 'vocabularies/',
                icon: <IoIcons.IoIosPaper />
            }
        ]
    },
    {
        title: 'Support',
        url: '/support',
        icon: <IoIcons.IoMdHelpCircle />
    }
];
export const { subNav } = SidebarData;
