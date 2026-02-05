
export default function SearchField() {
    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('Form submitted!');
    };

    return(
        <>
            <div id="searchField">
                <form onSubmit={handleSubmit}>
                    <input type="text" />
                    <br/>
                    <button type="submit">search</button>
                </form>
            </div>
        </>
    )
}