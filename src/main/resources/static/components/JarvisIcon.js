import html from "../modules/htm.js"
import {useTheme} from "../modules/material-ui.js"

function JarvisIcon(props) {
    return html`
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" width=${props.size} style=${{padding: props.padding}}>
            <defs>
                <style>
                    .cls-1 {
                        fill: ${useTheme().palette.text.primary};
                    }

                    .cls-2 {
                        fill: ${useTheme().palette.primary.main};
                    }
                </style>
            </defs>
            <polygon class="cls-1" points="3 4 8 9 13 4 3 4" />
            <path class="cls-2" d="M0,0H0A2,2,0,0,0,2,2H14a2,2,0,0,0,2-2H0Z" />
            <path class="cls-2" d="M0,4l8,8,8-8v.17A6.84,6.84,0,0,1,14,9h0L8,15,2,9H2A6.84,6.84,0,0,1,0,4.17Z" />
            <path class="cls-1" d="M0,10l6,6H6a6,6,0,0,1-6-6Z" />
            <path class="cls-1" d="M10,16l6-6h0a6,6,0,0,1-6,6Z" />
        </svg>
    `;
}

export default JarvisIcon;