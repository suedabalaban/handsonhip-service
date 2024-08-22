from transformers import pipeline

def generate_text(prompt):
    pipe = pipeline("text-generation", model="openai-community/gpt2")
    result = pipe(prompt, max_length=100, num_return_sequences=1)
    return result[0]['generated_text']

if __name__ == "__main__":
    import sys
    prompt = sys.argv[1]
    print(generate_text(prompt))
