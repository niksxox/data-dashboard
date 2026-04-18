import streamlit as st
import pandas as pd
import plotly.express as px

st.set_page_config(page_title="Netflix Analytics", layout="wide")
st.title("🎬 Netflix Data Insights Dashboard")

uploaded_file = st.file_uploader("Upload Netflix CSV", type=["csv"])

if uploaded_file:
    df = pd.read_csv(uploaded_file)
    
    # Simple Data Cleaning
    df['type'] = df['type'].fillna('Unknown')
    
    # Insights Section
    col1, col2 = st.columns(2)
    with col1:
        st.metric("Total Titles", len(df))
    with col2:
        st.metric("Total Countries", df['country'].nunique())

    # Charts Section
    st.subheader("Content Distribution: Movies vs TV Shows")
    fig = px.pie(df, names='type', color_discrete_sequence=['#E50914', '#564d4d'])
    st.plotly_chart(fig)

    st.subheader("Top 10 Countries by Content")
    top_countries = df['country'].value_counts().head(10)
    st.bar_chart(top_countries)

    st.subheader("Raw Data Preview")
    st.dataframe(df.head(10))
else:
    st.info("Please upload a netflix_titles.csv file")