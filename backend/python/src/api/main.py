from flask import Flask,request,jsonify
from astrofiles.astroSearches import AstronomySearch
app = Flask(__name__)

@app.route('/hello', methods=['GET'])
def hello():
    query = request.args.get('query',type=str)

    data = AstronomySearch.search(query)
    return jsonify(str(data))

if __name__ =='__main__':
    app.run(host='localhost',port = 5000,debug=True)