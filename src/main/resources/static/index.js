import ReactDOM from './modules/react-dom.js';
import html from './modules/htm.js';
import App from "./App.js"

ReactDOM.render(html`
    <${React.StrictMode}>
        <${App}>
    <//>
    `, document.body);