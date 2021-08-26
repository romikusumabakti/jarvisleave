import { html, render } from './js/standalone.module.js';
import App from "./App.js";

render(
    html`
        <${App}/>
    `,
    document.body
);