import html from "../modules/htm.js"
import {Link, Stack, Typography, useTheme} from "../modules/material-ui.js"
import JarvisIcon from "./JarvisIcon.js"

function JarvisLogo(props) {
    return html`
        <${Stack} direction="row" spacing=${1}>
            <${JarvisIcon} size=${props.size}/>
            <${Typography} variant="h6" fontSize=${props.size}>
                <span style=${{color: useTheme().palette.primary.main}}>Jarvis</span>
                <span style=${{color: useTheme().palette.text.primary}}>Leave</span>
            <//>
        <//>
    `;
}

export default JarvisLogo;