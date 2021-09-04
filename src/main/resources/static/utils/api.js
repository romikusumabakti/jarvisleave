async function api(url, method, body) {
    const token = localStorage.getItem('token');
    return await fetch('/api' + url, {
        method: method,
        headers: {
            'Authorization': 'Bearer ' + token,
            'Content-Type': 'application/json',
        },
        body: body,
    })
}

export default api;