import React from '../modules/react.js';
import {Container, Divider, Grow, Link, Paper, Stack} from '../modules/material-ui.js';
import html from '../modules/htm.js';
import Footer from "../fragments/Footer.js"
import JarvisLogo from "../components/JarvisLogo.js"

function About() {
    return html`
        <${Stack} minWidth=${0} flexGrow=${1}>
            <${Container} component="main" maxWidth="sm" sx=${{p: 3}}>
                <${Grow} in=${true} style=${{ transformOrigin: '50% 0' }}>
                    <${Paper} sx=${{p: 4}}>
                        <${JarvisLogo} size=${40} />
                        <p>
                            Versi 1.0.0 Alpha
                        </p>
                        <p>
                            Framework Back-End: Spring Framework<br />
                            Framework Front-End: React<br />
                            Library: Material-UI<br />
                            Metode otentikasi: JWT<br />
                            Repository GitHub:${' '}
                            <${Link} href="https://github.com/romikusumabakti/jarvisleave" target="_blank" sx=${{ wordBreak: 'break-all' }}>
                            https://github.com/romikusumabakti/jarvisleave
                            <//>
                        </p>
                        <p style=${{ marginBottom: 0 }}>
                            © 2021 PUB Angkatan 18 (Jarvis)<br />
                            Hak cipta dilindungi oleh undang-undang.
                        </p>
                    <//>
                <//>
            <//>
            <${Divider}/>
            <${Footer}/>
        <//>
    `;
}

export default About;