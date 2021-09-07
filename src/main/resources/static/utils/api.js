export async function api(url, method, body) {
    const token = localStorage.getItem('token');
    return await fetch('/api' + url, {
        method: method,
        headers: {
            'Authorization': 'Bearer ' + token,
        },
        body: body,
    })
}

export async function jsonApi(url, method, bodyJson) {
    const token = localStorage.getItem('token');
    return await fetch('/api' + url, {
        method: method,
        headers: {
            'Authorization': 'Bearer ' + token,
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(bodyJson),
    })
}