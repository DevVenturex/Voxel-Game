#version 330 core

in vec4 fColor;
in vec2 fTexCoord;

out vec4 color;

uniform sampler2D texture_sampler;
void main() {
    color = texture(texture_sampler, fTexCoord) + fColor;
}