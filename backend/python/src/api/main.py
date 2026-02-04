from flask import Flask,request,jsonify
from astrofiles.astroSearches import AstronomySearch
app = Flask(__name__)
searcher = 0