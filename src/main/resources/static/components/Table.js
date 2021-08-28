import {html} from '../js/standalone.module.js';
import {useEffect} from "../js/hooks.module.js";

function Table(props) {

    return html`
        <div class="mdc-data-table">
            <div class="mdc-data-table__table-container">
                <table class="mdc-data-table__table" aria-label=${props.label}>
                    ${props.children}
                </table>
            </div>
        </div>
    `;
}

export default Table;