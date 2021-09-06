import React from '../modules/react.js';
import {Divider, Stack} from '../modules/material-ui.js';
import html from '../modules/htm.js';
import Footer from "../fragments/Footer.js"

function Home() {
    return html`
        <${Stack} minWidth=${0} flexGrow=${1}>
            
            <${Divider}/>
            <${Footer}/>
        <//>
    `;
}

export default Home;