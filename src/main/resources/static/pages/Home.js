import React, {useContext} from '../modules/react.js';
import {Button, Container, Divider, Grow, Paper, Stack, Typography} from '../modules/material-ui.js';
import html from '../modules/htm.js';
import Footer from "../fragments/Footer.js"
import JarvisLogo from "../components/JarvisLogo.js"
import {Link} from "../modules/react-router-dom.js"
import {AuthContext} from "../App.js"

function Home() {

    const {user} = useContext(AuthContext);

    return html`
        <${Stack} minWidth=${0} flexGrow=${1}>
            <${Container} component="main" maxWidth="md" sx=${{p: 3}}>
                <${Grow} in=${true} style=${{ transformOrigin: '50% 0' }}>
                    <${Paper} sx=${{p: 4}}>
                        <${JarvisLogo} size=${24} />
                        <${Typography} variant="h5" sx=${{pt: 2}}>
                            Selamat datang!
                        <//>
                        <p>
                            Selamat datang di sistem manajemen cuti tercanggih di dunia.
                        </p>
                        <${Stack} direction="row" justifyContent="flex-end">
                            ${user ? html`
                                <${Button} variant="contained" component=${Link} to="/panel">
                                    Buka panel
                                <//>
                            ` : html`
                                <${Button} variant="contained" component=${Link} to="/login">
                                    Login
                                <//>
                            `}
                        <//>
                    <//>
                <//>
            <//>
            <${Divider}/>
            <${Footer}/>
        <//>
    `;
}

export default Home;